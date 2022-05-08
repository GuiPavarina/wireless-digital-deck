const express = require('express');
const apiRouter = express.Router();
const status = require('http-status-codes');

const hashRepository = require('../../db/hashRepository');
const shortcutRepository = require('../../db/shortcutRepository');

apiRouter.get(`/hashes/:hash`, async (req, res) => {
    const { hash } = req.params;
    hashRepository.findByHash(hash)
        .then(async users => {
            const user = users[0];
            const user_shortcuts = await recoverShortcutsFor(user);
            if (user_shortcuts) {
                res.status(status.StatusCodes.OK).json(user_shortcuts);
            } else {
                res.status(status.StatusCodes.BAD_REQUEST).json({ error: `Invalid hash: ${hash}` });
            }
        })
        .catch(err => {
            res.status(status.StatusCodes.INTERNAL_SERVER_ERROR).json({
                message: `Error finding the profile for hash ${hash} - ${err.message}`
            });
        });
});

function recoverShortcutsFor(user) {
    if (!user) {
        return null;
    }
    return new Promise(resolve => {
        shortcutRepository.findByUser(user.hash_user)
            .then(decks => {
                const deck = decks.map(shortcut => {
                    return {
                        id: shortcut.shortcut_order,
                        key: shortcut.shortcut_key,
                        app: shortcut.application_name,
                        name: shortcut.shortcut_name,
                        modifiers: shortcut.modifiers.split(','),
                    }
                });
                resolve(deck);
            })
            .catch(err => {
                throw new Error(`Error finding the profile for hash ${hash} - ${err.message}`);
            });
    });
}

module.exports = apiRouter;